package com.mdg.sociallogintopaymentspring_bootjpa.service;

import com.mdg.sociallogintopaymentspring_bootjpa.dto.MemberDto;
import com.mdg.sociallogintopaymentspring_bootjpa.model.Member;
import com.mdg.sociallogintopaymentspring_bootjpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public void insertMember(MemberDto memberDto) {
        memberRepository.save(memberDto.toEntity());
    }

    @Override
    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMember(MemberDto memberDto) throws Exception {
        return memberRepository.findById(memberDto.getId()).orElseThrow(Exception::new);
    }

    @Override
    @Transactional
    public void updateMember(MemberDto memberDto) throws Exception {
        Member member = memberRepository.findById(memberDto.getId()).orElseThrow(Exception::new);
        member.update(memberDto.getId(), memberDto.getPassword(), memberDto.getName(), memberDto.getBirthday(), memberDto.getAddress(), memberDto.getPhone1(), memberDto.getPhone2(), memberDto.getPhone3(), memberDto.getAuthority());
    }

    @Override
    public void deleteMember(MemberDto memberDto) {
        memberRepository.delete(memberDto.toEntity());
    }
}
