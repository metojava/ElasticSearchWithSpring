package com.tv.pagableservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tv.model.Video;
import com.tv.pageablerepos.VideoPagableRepo;

@Service("videoPagableService")
@Transactional
public class VideoPagableService {

	private static final int page_size = 10;

	@Autowired
	private VideoPagableRepo videoPagableRepo;

	public Page<Video> findAllinRange(int start, int num, boolean ascen,
			String fieldName) {
		if (fieldName.isEmpty() || fieldName.equals(""))
			fieldName = "name";
		Sort.Direction direction = null;
		if (ascen)
			direction = Sort.Direction.ASC;
		else
			direction = Sort.Direction.DESC;

		if (num == 0)
			num = page_size;
		PageRequest request = new PageRequest(start, num, direction, fieldName);
		return videoPagableRepo.findAll(request);
	}

	List<Video> findByName(String name) {
		return videoPagableRepo.findByName(name);
	}

	public Video findById(Integer id) {

		return videoPagableRepo.findOne(id);
	}

	public Video save(Video video) {

		return videoPagableRepo.save(video);
	}

	public void delete(Integer id) {
		videoPagableRepo.delete(id);
	}

}
