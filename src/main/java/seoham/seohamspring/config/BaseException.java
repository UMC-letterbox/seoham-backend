<<<<<<< HEAD
package seoham.seohamspring.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception{

    private BaseResponseStatus status;
}
=======
package seoham.seohamspring.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends Exception {
    private BaseResponseStatus status;
}
>>>>>>> 5a12ce1c2bd7eef06011995f838c5ba941d0d21e
