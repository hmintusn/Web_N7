package webgr7.hotelbk.service;

import webgr7.hotelbk.model.BookedRoom;

import java.util.List;

public interface BookedRoomService {
    public List<BookedRoom> getAllBookedRooms();

    public BookedRoom createBookedRoom(BookedRoom bookedRoom);

    public BookedRoom getBookedRoomById(Long bookedRoomId);

    public boolean updateBookedRoomById(Long bookedRoomId, BookedRoom bookedRoom);

    public void deleteBookedRoom(Long bookedRoomId);
}
