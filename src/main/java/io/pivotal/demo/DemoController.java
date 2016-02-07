package io.pivotal.demo;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class DemoController {
	
	@RequestMapping(method=RequestMethod.GET, value="/")
	@ResponseBody
	public String ping()
	{
		return "The demo controller is up and running";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/contents")
	@ResponseBody
	public String getFileContents()
	{
		try
		{
			Resource resource = new ClassPathResource("example.txt");
			File aFile = resource.getFile();
			byte[] encoded = Files.readAllBytes(aFile.toPath());
			return new String(encoded, StandardCharsets.UTF_8);
		}
		catch(Exception ex)
		{
			return "An exception occurred: " + ex.getMessage();
		}
	}

}
