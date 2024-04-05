
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sita.service2.payload.ApiResponse;
import com.sita.service2.payload.UserDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserInfoControllerTest {

	@Mock
	private UserDto dto;

	@InjectMocks
	private UserInfoController controller;

	@Test
	public void add_User_Info_Test() {
		// Create a sample userDto
		UserDto userDto = UserDto.builder().user("admin").workstation("CCUICKB0F1").status("Success")
				.message("user exist in database and has access to given workstation").build();

		 // Create mock dependencies
        UserDto mockedUserDto = mock(UserDto.class);

        // Mock the behavior of the mocked UserDto
        when(mockedUserDto.getUser()).thenReturn("admin");
        when(mockedUserDto.getWorkstation()).thenReturn("CCUICKB0F1");
        when(mockedUserDto.getStatus()).thenReturn("Success");
        when(mockedUserDto.getMessage()).thenReturn("user exist in database and has access to given workstation");

		// Call the method
		ResponseEntity<ApiResponse<UserDto>> responseEntity = controller.addUserInfo(userDto);

		// Verify that the response entity is not null
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		// Verify that the response entity body is the same as the input userDto
		assertEquals(dto, responseEntity.getBody());

		// Verify that the addUserInfo method was called once with the correct argument
		verify(controller, times(1)).addUserInfo(userDto);
	}
}
