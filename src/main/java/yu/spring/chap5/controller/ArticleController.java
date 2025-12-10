package yu.spring.chap5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import yu.spring.chap5.model.Article;
import yu.spring.chap5.model.ArticleRepository;

@Controller
@RequestMapping("/article")
public class ArticleController {
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("articles", articleRepository.findAll());
        return "listArticle";
    }
}
