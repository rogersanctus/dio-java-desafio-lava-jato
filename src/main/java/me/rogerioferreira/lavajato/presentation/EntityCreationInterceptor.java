package me.rogerioferreira.lavajato.presentation;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.rogerioferreira.lavajato.infra.models.WithId;

@RestControllerAdvice
public class EntityCreationInterceptor implements ResponseBodyAdvice<Object> {
  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return returnType.getMethod().isAnnotationPresent(PostMapping.class)
        && returnType.getMethod().getReturnType() == ResponseEntity.class;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
      ServerHttpResponse response) {

    if (response instanceof ServletServerHttpResponse servletServerHttpResponse) {
      var httpServletResponse = servletServerHttpResponse.getServletResponse();

      if (httpServletResponse.getStatus() == HttpStatus.CREATED.value() && body instanceof WithId withId) {
        var location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(withId.getId())
            .toUri();

        response.getHeaders().setLocation(location);
      }
    }

    return body;
  }

}
