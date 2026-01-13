package ca.tetervak.dicegame.controller;

import ca.tetervak.dicegame.domain.DiceRollData;
import ca.tetervak.dicegame.service.CookieDataService;
import ca.tetervak.dicegame.service.DiceRollerService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class DiceGameController {

    private final Logger log = LoggerFactory.getLogger(DiceGameController.class);

    private final DiceRollerService rollerService;
    private final CookieDataService cookieDataService;

    public DiceGameController(
            DiceRollerService rollerService,
            CookieDataService cookieDataService) {
        this.rollerService = rollerService;
        this.cookieDataService = cookieDataService;
    }

    @GetMapping(value = {"/", "/dice-game"})
    public ModelAndView diceGame(
            @RequestParam(defaultValue = "3") int numberOfDice,
            @RequestParam(defaultValue = "false") boolean isRolled,
            HttpServletResponse response,
            @CookieValue(value="rollData", defaultValue = "") String cookieValue
    ){
        log.trace("diceGame() is called");
        log.debug("numberOfDice = {}", numberOfDice);
        log.debug("isRolled = {}", isRolled);

        if(isRolled){
            log.debug("dice is rolled");
            DiceRollData rollData;
            if(numberOfDice > 0 && numberOfDice <= 5){
                rollData = rollerService.getRollData(numberOfDice);
            }else{
                log.warn("the numberOfDice is out of the range {}", numberOfDice);
                rollData = rollerService.getRollData(3);
            }
            log.debug("rollData = {}", rollData);
            Cookie cookie = new Cookie(
                    "rollData",
                    cookieDataService.encodeRollData(rollData));
            cookie.setMaxAge(24*60*60);
            response.addCookie(cookie);
            return  new ModelAndView("game-result", "rollData", rollData);
        }else{
            log.debug("dice is not rolled");
            if(cookieValue.isEmpty()){
                log.debug("no previously saved state in the cookie");
                return new ModelAndView("game-start");
            }else{
                log.debug("restoring previous state from the cookie");
                try{
                    DiceRollData rollData = cookieDataService.decodeRollData(cookieValue);
                    return new ModelAndView("game-result", "rollData", rollData);
                }catch(Exception e){
                    log.error("could not recover the data from the cookie");
                    return new ModelAndView("game-start");
                }
            }
        }
    }

    @GetMapping("/reset")
    public String reset(HttpServletResponse response){
        // remove the cookie and redirect to the default page
        Cookie cookie = new Cookie("rollData","whatever");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

    @ModelAttribute("localDate")
    LocalDate getlLocalDate(){
        return LocalDate.now();
    }

}
