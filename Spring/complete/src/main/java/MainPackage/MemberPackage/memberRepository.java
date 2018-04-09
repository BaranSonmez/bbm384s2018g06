package MainPackage.MemberPackage;

import org.springframework.data.repository.CrudRepository;


public interface memberRepository extends  CrudRepository<member, Long>{
	
    member findDistinctByUsernameAndPassword(String username, String password);
    member findDistinctByUsername(String username);

}
