package com.study.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.study.dto.AttachDTO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Slf4j
public class UploadAjaxController {
	
	
//	@GetMapping("/uploadAjax")
//	public String uploadAjaxForm() {
//		log.info("ajax 업로드 폼 요청");
//		
//		return "uploadform_ajax";
//	}
	
	
	//upload.js에서 값이 넘어옴
	@PreAuthorize("isAuthenticated()")  //인증된 정보를 가지고 있는지 확인
	@PostMapping("/uploadAjax")
	public ResponseEntity<List<AttachDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("ajax 업로드 폼 요청");

		
		List<AttachDTO> attachList = new ArrayList<AttachDTO>();
		
		
		// 업로드 기본 폴더 지정
		// 기본 폴더에 자료가 너무 많이 생기니 세부 폴더 지정
		String uploadBasicPath = "d:\\upload";
		
		// 업로드 세부 폴더 지정 "2022\05\06"
		String uploadFolderPath = getFolder();
		
		//전체 업로드 패스 생성 "d:\\upload\\2022\05\06"
		File uploadPath = new File(uploadBasicPath, uploadFolderPath);
		
		if(!uploadPath.exists()) {//폴더가 없다면 폴더들 생성
			uploadPath.mkdirs(); //여러개 생성해야하니.... mkdirs사용
		}
		
		//업로드 파일명
		String uploadFileName = "";

		File save = null;
		
		for (MultipartFile f : uploadFile) {
			
			//잘 실행되는지 확인
//			log.info("파일명 : " + f.getOriginalFilename());
//			log.info("파일크기 : " + f.getSize());
			
			//파일명 가져오기
			String oriFileName = f.getOriginalFilename();
			
			//중복 파일명 해결하기
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString()+"_"+oriFileName;
			
			//업로드 파일 객체 생성
			AttachDTO attachDto = new AttachDTO();
			attachDto.setUploadPath(uploadFolderPath);
			attachDto.setFileName(oriFileName);
			attachDto.setUuid(uuid.toString());
			
			
			save = new File(uploadPath, uploadFileName);

			try {
				
				if(checkImageType(save)) {
					attachDto.setFileType(true);
					
					//이미지이면 썸네일 저장
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_" + uploadFileName));
					InputStream in = f.getInputStream();
					Thumbnailator.createThumbnail(in, thumbnail, 80, 80);
					in.close();
					thumbnail.close();
					
					
				}
				
				// 파일 저장
				f.transferTo(save);
				attachList.add(attachDto);
				
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<List<AttachDTO>>(attachList, HttpStatus.OK);
		
	}
	
	//썸네일 이미지 보여주기
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName){
		log.info("이미지 요청" + fileName);
		
		File file = new File("d:\\upload\\"+fileName);
		
		ResponseEntity<byte[]> image = null;
		
		HttpHeaders header = new HttpHeaders();
		try {
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			image = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
		
	}
	
	
	//UploadController에서 이동
	// 파일 다운로드
	@GetMapping(path = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> dounloadFile(String fileName){
		log.info("다운로드 요청 -" + fileName);
		Resource resource = new FileSystemResource("d:\\upload\\"+fileName);
		String resourceUidName = resource.getFilename();
		
		//uuid 값 제거(서버에서 보내는 유효이름 제거)
		String resourceName = resourceUidName.substring(resourceUidName.indexOf("_")+1);
		
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition",
					"acctachment;filename=" + new String(resourceName.getBytes("utf-8"),"ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	//서버 파일 삭제 (deleteFile) : x버튼 클릭시
	//성공 or 실패 보내기
	@PreAuthorize("isAuthenticated()") //인증된 정보를 가지고 있는지 확인
	@PostMapping("/deleteFile") //js 경로랑 맞추기
	public ResponseEntity<String> deleteFile(String fileName, String type){
		
		log.info("파일 삭제 요청 " + fileName+ " " + type);
		
		try {
			//js에 들어올 때 인코딩 걸려있음.
			// fileCallPath가 인코딩이 걸려있기 때문에 원래대로 바꿔줘야함. ==> decoder 사용
			File file = new File("d:\\upload\\"+URLDecoder.decode(fileName, "utf-8"));
			
			file.delete(); //이미지인 경우 썸네일 삭제, 일반 파일은 파일삭제.
			
			//이미지인 경우 원본 파일 삭제
			if(type.equals("image")) {
				//원본 파일 경로
				String largeName = file.getAbsolutePath().replace("s_","");
				file = new File(largeName);
				file.delete();
				
			}		
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
		
		
	}
	
	
	
	//이미지 파일 여부
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image"); // image/jpeg, image/gif, image/bmp, image/png
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	//폴더 생성 메소드
	//폴더를 일자별로 저장하겠다.
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//오늘 날짜 
		Date date = new Date();//Wed Dec 08 16:40:05 KST 2022
		
		//"2022-05-06" 오늘 날짜
		String str = sdf.format(date);
		
		//window:\, unix:/ 로사용
		return str.replace("-", File.separator); //-를 파일 구분하는 대체 => 2022\05\06 \부분을 -로 변경
	
	}
	
	
	
	
	
}
