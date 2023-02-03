package com.situ.hotel.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.situ.hotel.entity.Result;
import com.situ.hotel.util.UploadUtil;

/**
 * 文件上传的Controller接口
 * @author Zhi Jiu
 *
 */
@RestController
public class UploadController {

	//从配置文件中读取文件保存的路径
	@Value("${upload.path}")
	private String path;
	
	/**
	 * 文件上传的方法
	 */
	@PostMapping("/upload")
	public Result upload(MultipartFile file) {	//file接受上传的文件
		
		// 获取上传的文件,保存到硬盘中.
		String fileName = UploadUtil.save(file, path);
		
		// 返回数据
		return Result.success(0, fileName);
		
	}
}
