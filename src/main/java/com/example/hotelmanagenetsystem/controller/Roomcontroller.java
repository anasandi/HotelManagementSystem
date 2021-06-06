package com.example.hotelmanagenetsystem.controller;

import com.example.hotelmanagenetsystem.model.Rooms;
import com.example.hotelmanagenetsystem.service.RoomService;
import com.sun.org.apache.xpath.internal.operations.Mod;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Roomcontroller {

    private final RoomService roomService;

    public Roomcontroller(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/room")
    public String createRooms(Model model){
        model.addAttribute("rooms",new Rooms());
        return "admin/roomForm";
    }
    @PostMapping("/room")
    public String ProcessRoom(@Validated @ModelAttribute("rooms") Rooms rooms, BindingResult result,Model model,@RequestParam("fileImage")MultipartFile multipartFile) throws IOException{
        if(result.hasErrors()){
            model.addAttribute("rooms",rooms);
            return "admin/roomForm";
        }
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        rooms.setPhotos(fileName);
        Rooms savedRoom=roomService.create(rooms);
        String uploadDir = "hotel-photos/" + savedRoom.getId();
        Path uploadPath = Paths.get(uploadDir);
      
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            System.out.println(filePath.toFile().getAbsolutePath());
            
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }   
        return "redirect:/rooms";
    }
   
    @GetMapping("/rooms")
    public String showRooms(Model model){
        model.addAttribute("rooms",roomService.findAll());
        model.addAttribute("deletesuccess",model.containsAttribute("delete"));
        model.addAttribute("updatesuccess",model.containsAttribute("update"));
        return "admin/rooms";
    }

    @GetMapping("/room/{id}")
    public String showRoomdetail(Model model,@PathVariable("id") long id){
        model.addAttribute("room",roomService.findById(id));
        return "admin/roomdetail";

    }
    @GetMapping("room/delete/{id}")
    public String roomDelete(@PathVariable("id") long id, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("delete",true);

        roomService.delete(id);
        return "redirect:/rooms";

    }
    @GetMapping("room/update/{id}")
    public String updateRoom(Model model,@PathVariable("id") long id){
        model.addAttribute("room",roomService.findById(id));
        this.updateId=id;
        return "admin/updateRoomForm";
    }
    
    private long updateId;

    @PostMapping("room/update")
     public String processUpdate(Rooms rooms,RedirectAttributes redirectAttributes){
         roomService.update(updateId,rooms);
         redirectAttributes.addFlashAttribute("update",true);
         return "redirect:/rooms";
     }
     @GetMapping("/room/findroom")
     public String searchRoom(Model model, HttpServletRequest request){

      Rooms rooms =roomService.findByRoomNumber(request.getParameter("roomNumber"));
       model.addAttribute("room",rooms);

         return "redirect:/room/"+rooms.getId();
     }
    
     private Logger logger= LoggerFactory.getLogger(this.getClass());

}
