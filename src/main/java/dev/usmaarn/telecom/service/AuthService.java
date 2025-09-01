package dev.usmaarn.telecom.service;
import dev.usmaarn.telecom.dto.request.CreateAccountDto;
import dev.usmaarn.telecom.dto.response.TokenResponseDto;
import dev.usmaarn.telecom.mapper.TokenMapper;
import dev.usmaarn.telecom.model.Token;
import dev.usmaarn.telecom.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final TokenService tokenService;

    public AuthService(UserService userService, TokenService tokenService){
        this.userService = userService;
        this.tokenService = tokenService;
    }

    public TokenResponseDto register(CreateAccountDto createAccountDto){
        User user = userService.createUser(createAccountDto);
        Token token = tokenService.createToken(user);
        return TokenMapper.mapToDto(token);
    }
}
