package springbook;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Import(SqlServiceContext.class)
public @interface EnableSqlService {
}
