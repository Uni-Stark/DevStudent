package com.hours22.devstudent.AOP;

import com.hours22.devstudent.Entity.SequenceID;
import com.hours22.devstudent.Repository.SequenceIDRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MutationAspect {
    private SequenceIDRepository sequenceIDRepository;
    public MutationAspect(SequenceIDRepository sequenceIDRepository){
        this.sequenceIDRepository = sequenceIDRepository;
    }

    @Before("execution(* com.hours22.devstudent.Command.Mutation.create*(..) )")
    public void beforeCreate(JoinPoint joinPoint) { // 해당 ID가 Sequence에 있나 없나 검사
        String methodName = joinPoint.getSignature().getName().substring(6);
        if(sequenceIDRepository.countBy_id(methodName) == 0) {
            System.out.println(methodName + "이 Sequence에 없습니다!");
            SequenceID sequenceID = new SequenceID(methodName, 0);
            sequenceIDRepository.save(sequenceID);
            System.out.println(methodName + "을 Sequence에 생성 완료!");
        }
    }
}
