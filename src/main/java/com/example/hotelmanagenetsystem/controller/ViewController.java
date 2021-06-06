package com.example.hotelmanagenetsystem.controller;

import com.example.hotelmanagenetsystem.config.PdfReport;
import com.example.hotelmanagenetsystem.model.Bookings;
import com.example.hotelmanagenetsystem.model.RoomStatus;
import com.example.hotelmanagenetsystem.model.Rooms;
import com.example.hotelmanagenetsystem.model.UserProfile;
import com.example.hotelmanagenetsystem.repository.UserProfileRepository;
import com.example.hotelmanagenetsystem.service.BookingService;
import com.example.hotelmanagenetsystem.service.RoomService;
import com.example.hotelmanagenetsystem.service.UserDetailServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class ViewController {

    private final RoomService roomService;
   private final UserDetailServiceImpl userDetailService;
    private final BookingService bookingService;
    //private Logger logger = Logger.getLogger(getClass().getName());


    public ViewController(RoomService roomService, BookingService bookingService,UserDetailServiceImpl userDetailService) {
        this.roomService = roomService;
        this.userDetailService = userDetailService;
        this.bookingService = bookingService;
        //UserDetailServiceImpl userDetailService
    }

    @GetMapping("view/rooms")
    public String viewAlrooms(Model model){
        model.addAttribute("rooms",roomService.findAll());
        return "view/rooms";
    }
    @GetMapping("view/rooms/{id}")
    public String viewRoomsdetail(Model model,@PathVariable("id") long id){
        model.addAttribute("room",roomService.findById(id));
               this.roomId=id;
                return "view/room";
    }
   @GetMapping("/view/userprofile/{id}")
    public String userProfileCreate(Model model,@PathVariable("id") long roomid){
        model.addAttribute("userprofile",new UserProfile());
        return "view/profile";
    }
    @PostMapping("/view/userprofile")
    public String userProfileProcess(@Validated @ModelAttribute("userprofile") UserProfile userProfile,BindingResult bindingResult,Model model){
    	  //String email = userProfile.getEmail();
    	if (bindingResult.hasErrors()) {
            model.addAttribute("userprofile",userProfile);
			return "view/profile";
			
		}
    	/*Optional<UserProfile> existingEmail = userDetailService.findByEmail(email);
        if (existingEmail != null) {
            model.addAttribute("userprofile", new UserProfile());
            model.addAttribute("registrationError", "User already exist in database");

            logger.warn("User name already exists.");


            return "view/profile";
        }*/

    	UserProfile userProfile1  =this.userDetailService.register(userProfile);
        this.userProfileId=userProfile1.getId();
        return "redirect:/login";
	}
    
    
    /*@PostMapping("/processingRegistrationForm")
    public String registry(@Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
                           BindingResult theBindingResult,
                           Model theModel) {
        String username = theCrmUser.getUserName();
        logger.info("Processing registration form for: " + username);

        if (theBindingResult.hasErrors()) {
            return "registration-form";
        }

        User existingUser = service.findByUserName(username);
        if (existingUser != null) {
            theModel.addAttribute("crmUser", new CrmUser());
            theModel.addAttribute("registrationError", "User already exist in database");

            logger.warning("User name already exists.");


            return "registration-form";
        }

        service.save(theCrmUser);

        logger.info("Successfully created user: " + username);

        return "registration-confirmation";
      */
    /*@RequestMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, Model model) {
        if (error != null) {
            model.addAttribute("error", "Wrong username or password!");
        }

        if (logout != null) {
            model.addAttribute("msg", "You have successfully logged out.");
        }
        return "login";
    }*/
    @GetMapping("/view/booking")
    public String bookingCreate(Model model){
        model.addAttribute("booking",new Bookings());
        return "view/bookingForm";
    }
    @PostMapping("/view/booking")
    @Transactional
    public String bookingProcess(@Validated @ModelAttribute("booking") Bookings bookings, RedirectAttributes redirectAttributes){
        if(bookings.isBookingCancelled()){
            return "redirect:/home";
        }
        bookings.setUserProfile(this.userDetailService.findyById(userProfileId));
        String bookingNum="Delux"+(++bookingNumber)+new Random().nextInt(1000);
        bookings.setBookingNumber(bookingNum);
        bookings.setRooms(this.roomService.findById(roomId));
     Rooms room =roomService.findById(roomId);
      // double roomPrice=room.getRoomType().getPrice();
      // double balance=(roomPrice-bookings.getInAdvance());
     //  bookings.setFullCharges(balance);
       bookings.setBookingCancelled(false);
        this.bookingService.create(bookings);
        redirectAttributes.addFlashAttribute("booking",true);
        redirectAttributes.addFlashAttribute("bookingNumber",bookingNum);


        room.setRoomStatus(RoomStatus.Reverse);
        return "redirect:/home";
    }
    
    @GetMapping("/bookinglists")
    public String showBookingList(Model model) {
    	model.addAttribute("bookinglists", bookingService.findAll());
    	return "admin/userbookinglist";
    }
    @GetMapping("/bookinglistreport/pdf")
    public ResponseEntity<InputStreamResource> showPdfResource(){

      ByteArrayInputStream bai = PdfReport.authorPdfViews(bookingService.findAll());
      HttpHeaders headers=new HttpHeaders();
      headers.add("Content-Disposition","inline;filename=userbookinglistsreport.pdf");
      return ResponseEntity.ok()
              .headers(headers)
              .contentType(MediaType.APPLICATION_PDF)
              .body(new InputStreamResource(bai));
    }
    
    private Long userProfileId;

    private Long roomId;
    private static int bookingNumber=111;
    private Logger logger= LoggerFactory.getLogger(this.getClass());
}
