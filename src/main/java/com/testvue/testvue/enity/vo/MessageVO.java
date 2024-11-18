package com.testvue.testvue.enity.vo;

import com.testvue.testvue.enity.po.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageVO {

    private List<Message> senderToMessage;

    private List<Message> receiverToMessage;
}
