package ray.springframework.mvc.recipeapp.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Device {
    private String token;
    private String platform;
    private String name;
    private Boolean optin;
}
