package com.thitracnghiem.dto;

public class SubjectsDTO extends AbstractDTO<SubjectsDTO> {
		private String name;
		private String level;
		private String idLevel;
		
		private LevelDTO levelDTO;
		
		public LevelDTO getLevelDTO() {
			return levelDTO;
		}
		public void setLevelDTO(LevelDTO levelDTO) {
			this.levelDTO = levelDTO;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
		}
		public String getIdLevel() {
			return idLevel;
		}
		public void setIdLevel(String idLevel) {
			this.idLevel = idLevel;
		}
		
}
