package tehnicne.vescine.naloga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tehnicne.vescine.naloga.entity.Member;
import tehnicne.vescine.naloga.service.MemberService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MemberController {
    private MemberService memberService;
    
    @Autowired
    public MemberController(MemberService theMemberService) {
        memberService = theMemberService;
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Member member = new Member();
        theModel.addAttribute("member", member);
        return "members/member-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("memberUsername") String memberUsername,
                                    Model theModel) {
        Member member = memberService.findAll().stream().filter(member1 -> member1.getUsername().equals(memberUsername)).findFirst().orElseThrow();
        theModel.addAttribute("member", member);
        return "members/member-form";
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listMembers(Model theModel) {
        //Get the employees from the database
        List<Member> members = memberService.findAll();

        // add to the spring model
        theModel.addAttribute("members", members);

        return "members/list-members"; //renders the html list-members.html
    }

    @PostMapping("/save")
    public String saveMember(@ModelAttribute("member") Member member) {
        if (member.getPw().isEmpty() || member.getPw().isBlank()) {
            member.setPw("{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q");
        } else {
            Optional<Member> tempMember = memberService.findAll().stream().filter(member1 -> member1.getUsername().equals(member.getUsername())).findFirst();
            if (tempMember.isPresent()) {
                if (!tempMember.get().getPw().equals(member.getPw())) {
                    member.setPw("{bcrypt}" + member.getPw());
                }
            }
        }
        member.setActive(1);
        memberService.save(member);

        return "redirect:/members/list";
    }

    @GetMapping("/delete")
    public String deleteMember(@RequestParam("memberUsername") String username) {
        memberService.deleteByUsername(username);
        return "redirect:/members/list";
    }
}
