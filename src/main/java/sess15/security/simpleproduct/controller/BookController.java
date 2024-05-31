package sess15.security.simpleproduct.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("book")
@RestController
public class BookController {
  @GetMapping("")
  public String getBookInString(){
    return "Ini book.";
  }
}
