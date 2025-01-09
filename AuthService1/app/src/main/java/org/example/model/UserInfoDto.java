package org.example.model;
// jo bs hmare code m use ho rhi hongi not for dataabases other services

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import org.example.entities.UserInfo;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
public class UserInfoDto  extends UserInfo {
    private String firstName;
    private String lastName;
    private int phoneNumebr;
    private String email;
}

