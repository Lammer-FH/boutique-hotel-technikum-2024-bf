package hotel.group_bf.controller;

import hotel.group_bf.dto.RoomDTO;
import hotel.group_bf.entity.Room;
import hotel.group_bf.mapping.Mapper;
import hotel.group_bf.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;
    private final Mapper mapper;

    @Autowired
    public RoomController(RoomService roomService, Mapper mapper) {
        this.roomService = roomService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return ResponseEntity.ok(mapper.mapToRoomDTOList(roomService.getAllRooms()));
    }

    @GetMapping("/page")
    public ResponseEntity<List<RoomDTO>> getRooms(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(mapper.mapToRoomDTOList(roomService.getRooms(paging).getContent()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        if (room != null) {
            return ResponseEntity.ok(mapper.mapToRoomDTO(room));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO) {
        Room room = mapper.mapToRoom(roomDTO);
        Room createdRoom = roomService.saveRoom(room);
        return ResponseEntity.ok(mapper.mapToRoomDTO(createdRoom));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoomById(id);
        return ResponseEntity.noContent().build();
    }
}
