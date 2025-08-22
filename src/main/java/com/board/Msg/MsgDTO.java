package com.board.Msg;

import org.springframework.stereotype.Component;

@Component
public class MsgDTO {

	private String id;
	private String title;
	private String content;
	private String sender_id;
	private String receiver_id;
	private String is_read;
	private int deleted_by_sender;
	private int deleted_by_receiver;
	private String created_at;
	private String read_at;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSender_id() {
		return sender_id;
	}
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}
	public String getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}
	public String getIs_read() {
		return is_read;
	}
	public void setIs_read(String is_read) {
		this.is_read = is_read;
	}
	public int getDeleted_by_sender() {
		return deleted_by_sender;
	}
	public void setDeleted_by_sender(int deleted_by_sender) {
		this.deleted_by_sender = deleted_by_sender;
	}
	public int getDeleted_by_receiver() {
		return deleted_by_receiver;
	}
	public void setDeleted_by_receiver(int deleted_by_receiver) {
		this.deleted_by_receiver = deleted_by_receiver;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getRead_at() {
		return read_at;
	}
	public void setRead_at(String read_at) {
		this.read_at = read_at;
	}
	@Override
	public String toString() {
		return "MsgDTO [id=" + id + ", title=" + title + ", content=" + content + ", sender_id=" + sender_id
				+ ", receiver_id=" + receiver_id + ", is_read=" + is_read + ", deleted_by_sender=" + deleted_by_sender
				+ ", deleted_by_receiver=" + deleted_by_receiver + ", created_at=" + created_at + ", read_at=" + read_at
				+ "]";
	}
	
	
	public MsgDTO(String id, String title, String content, String sender_id, String receiver_id, String is_read,
			int deleted_by_sender, int deleted_by_receiver, String created_at, String read_at) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.sender_id = sender_id;
		this.receiver_id = receiver_id;
		this.is_read = is_read;
		this.deleted_by_sender = deleted_by_sender;
		this.deleted_by_receiver = deleted_by_receiver;
		this.created_at = created_at;
		this.read_at = read_at;
	}
	public MsgDTO() {}
	
	
	
	
}
