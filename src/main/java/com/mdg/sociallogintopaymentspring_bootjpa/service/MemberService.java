package com.mdg.sociallogintopaymentspring_bootjpa.service;


import com.mdg.sociallogintopaymentspring_bootjpa.dto.MemberDto;
import com.mdg.sociallogintopaymentspring_bootjpa.model.Member;

import java.util.List;

public interface MemberService {
    void insertMember(MemberDto memberDto);

    List<Member> getMemberList();

    Member getMember(MemberDto memberDto) throws Exception;

    void updateMember(MemberDto memberDto) throws Exception;

    void deleteMember(MemberDto memberDto);
}
