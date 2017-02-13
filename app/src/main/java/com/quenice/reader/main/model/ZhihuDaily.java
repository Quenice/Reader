package com.quenice.reader.main.model;

import com.quenice.reader.common.model.Protection;

import java.util.List;

/**
 * 知乎日报model
 * Created by qiubb on 2017/2/10.
 */

public class ZhihuDaily implements Protection {
	private String date;
	private List<Story> stories;
	private List<TopStory> topStories;

	private static class Detail implements Protection {
		private int type;
		private long id;
		private String ga_prefix;
		private String title;

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getGa_prefix() {
			return ga_prefix;
		}

		public void setGa_prefix(String ga_prefix) {
			this.ga_prefix = ga_prefix;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
	}

	public static class Story extends Detail implements Protection {
		private List<String> images;

		public List<String> getImages() {
			return images;
		}

		public void setImages(List<String> images) {
			this.images = images;
		}
	}

	public static class TopStory extends Detail implements Protection {
		private String image;

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Story> getStories() {
		return stories;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}

	public List<TopStory> getTopStories() {
		return topStories;
	}

	public void setTopStories(List<TopStory> topStories) {
		this.topStories = topStories;
	}
}
