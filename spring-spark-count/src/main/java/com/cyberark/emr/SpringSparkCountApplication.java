package com.cyberark.emr;

import com.cyberark.emr.services.WordCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringSparkCountApplication implements CommandLineRunner {
// when running with spark-submit main class must be 'org.springframework.boot.loader.JarLauncher'
	@Autowired
	WordCountService service;

    @Autowired
	ApplicationContext ctx;

	public static void main(String[] args) {
		SpringApplication.run(SpringSparkCountApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("RUNNING ...");

		String[] words = {"i have four words", "two words" , "one"};
		System.out.println("WordCountService is being called :");
		Map<String, Long> countsMap = service.getCount(Arrays.stream(words).collect(Collectors.toList()));
		System.out.println("words list count :" );
		for (Map.Entry<String, Long> entry : countsMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
}
