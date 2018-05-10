package br.com.eleanor.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HomeController {
    @RequestMapping("/")
    fun index() ="index"
}