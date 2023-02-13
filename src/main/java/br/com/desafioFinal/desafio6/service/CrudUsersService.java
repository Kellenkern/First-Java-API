package br.com.desafioFinal.desafio6.service;

import br.com.desafioFinal.desafio6.dto.req.UserDtoReq;
import br.com.desafioFinal.desafio6.dto.res.ResUserDTO;
import br.com.desafioFinal.desafio6.model.User;
import br.com.desafioFinal.desafio6.repository.UsersRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CrudUsersService {

    @Autowired
    private final UsersRepository usersRepository;

    public CrudUsersService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }


    public ResUserDTO AddClient (UserDtoReq userDtoReq){

        User user = new User();

        user.setName(userDtoReq.getName());
        user.setOffice(userDtoReq.getOffice());
        user.setAge(userDtoReq.getAge());
        user.setRole(userDtoReq.getRole());
        user.setEmail(userDtoReq.getEmail());
        user.setUsername(userDtoReq.getUsername());
        user.setPassword(userDtoReq.getPassword());
        user.setTelephone(userDtoReq.getTelephone());
        user.setPhoto(userDtoReq.getPhoto());

        usersRepository.save(user);
        return new ResUserDTO(user);

    }

    public ResUserDTO UpdatedClient(Long id,UserDtoReq userDtoReq) {

        User selectedUser = usersRepository.findById(id).orElseThrow(() -> new ServiceException("Id não encontrado"));

        selectedUser.setId(id);
        selectedUser.setName(userDtoReq.getName());
        selectedUser.setOffice(userDtoReq.getOffice());
        selectedUser.setAge(userDtoReq.getAge());
        selectedUser.setRole(userDtoReq.getRole());
        selectedUser.setEmail(userDtoReq.getEmail());
        selectedUser.setUsername(userDtoReq.getUsername());
        selectedUser.setPassword(userDtoReq.getPassword());
        selectedUser.setTelephone(userDtoReq.getTelephone());
        selectedUser.setPhoto(userDtoReq.getPhoto());
        selectedUser.setIsDelited(false);

        usersRepository.save(selectedUser);
        return new ResUserDTO(selectedUser);
    }

    public Page<ResUserDTO> SeeClient (Pageable pagination){
        return this.usersRepository.findAllAndNotDeleted(pagination).map(ResUserDTO::of);
    }

    public Page<ResUserDTO> FindByName (String name,Pageable pagination ){
        return this.usersRepository.findByName(name, pagination).map(ResUserDTO::of);
    }

    public ResUserDTO FindById (Long id){
        User selectedUser = usersRepository.findById(id).orElseThrow(() -> new ServiceException("Id não encontrado"));
        return ResUserDTO.of(selectedUser);
    }

    public void DeleteClient (Long id){
         if (this.usersRepository.findById(id).isEmpty())
            throw new ServiceException("Usuário não existe");

        usersRepository.softDelit(id);
    }

}
