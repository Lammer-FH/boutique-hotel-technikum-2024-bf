package hotel.group_bf.controller;

import hotel.group_bf.dto.RoomDTO;
import hotel.group_bf.entity.Room;
import hotel.group_bf.mapping.Mapper;
import hotel.group_bf.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public List<RoomDTO> getAllRooms() {
        return mapper.mapToRoomDTOList(roomService.getAllRooms());
    }

    @GetMapping("/page")
    public List<RoomDTO> getRooms(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return mapper.mapToRoomDTOList(roomService.getRooms(paging).getContent());
    }

    @GetMapping("/{id}")
    public RoomDTO getRoomById(@PathVariable Long id) {
        return mapper.mapToRoomDTO(roomService.getRoomById(id));
    }

    @PostMapping
    public RoomDTO createRoom(@RequestBody RoomDTO roomDTO) {
        Room room = mapper.mapToRoom(roomDTO);
        Room createdRoom = roomService.saveRoom(room);
        return mapper.mapToRoomDTO(createdRoom);
    }

    @PutMapping("/{id}")
    public RoomDTO updateRoom(@PathVariable Long id, @RequestBody RoomDTO roomDTO) {
        Room room = mapper.mapToRoom(roomDTO);
        room.setId(id);
        Room updatedRoom = roomService.saveRoom(room);
        return mapper.mapToRoomDTO(updatedRoom);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoomById(id);
    }
}
