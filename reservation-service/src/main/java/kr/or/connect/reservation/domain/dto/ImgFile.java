package kr.or.connect.reservation.domain.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImgFile {
	private Integer id;
	private Integer user_id;
	private String file_name;
	private String save_file_name;
	private Integer file_length;
	private String content_type;
	private Integer delete_flag;


}
