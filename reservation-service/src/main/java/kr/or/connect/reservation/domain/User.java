package kr.or.connect.reservation.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	private Integer id;
	private String username;
	private String email;
	private String tel;
	private String nickname;
	private String sns_id;
	private String sns_type;
	private String sns_profile;
	private Integer admin_flag;
	private String create_date;
	private String modify_date;

}
