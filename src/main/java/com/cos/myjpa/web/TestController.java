package com.cos.myjpa.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.domain.post.Post;
import com.cos.myjpa.domain.post.PostRepository;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.post.dto.PostUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor	// @Autowired 대신 이걸로 주입시키자.
@RestController
public class TestController {
	
	private final PostRepository postRepository;

//	@PostMapping("/test/post")
//	public CommonRespDto<?> save(@RequestBody PostSaveReqDto postSaveReqDto) {	// title, content
//		
//		// 원래는 세션값을 넣어야 함.
//		User user = new User(1L, "irone", "1234", "irone@gmail.com", LocalDateTime.now());
//		
//		Post postEntity = postRepository.save(postSaveReqDto.toEntity());	// 실패 => Exception을 탄다.
//		postEntity.setUser(user);
//		
//		
//		return new CommonRespDto<>(1, "성공", postEntity);
//	}
	
	@GetMapping("/test/post")
	public CommonRespDto<?> findAll() {
		List<Post> postsEntity = postRepository.findAll();
		return new CommonRespDto<>(1, "성공", postsEntity);
	}
	
	@GetMapping("/test/post/{id}")
	public CommonRespDto<?> findById(@PathVariable Long id) {
		Post postEntity = postRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		return new CommonRespDto<>(1, "성공",postEntity);
	}
	
	@PutMapping("/test/post/{id}")
	public CommonRespDto<?> update(@PathVariable Long id, @RequestBody PostUpdateReqDto postUpdateReqDto) {
		Post postEntity = postRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		postEntity.setTitle(postUpdateReqDto.getTitle());
		postEntity.setContent(postUpdateReqDto.getContent());
		
		Post postUpdateEntity = postRepository.save(postEntity);	// 더티 체킹을 사용해야 하는데 그러려면 @Service 만들어야 가능함 !
		
		return new CommonRespDto<>(1, "성공", postUpdateEntity);
	}
	
	@DeleteMapping("test/post/{id}")
	public CommonRespDto<?> delete(@PathVariable Long id) {
		postRepository.deleteById(id);
		return new CommonRespDto<>(1, "성공", null);
	}
}
