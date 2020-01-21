package com.example.petclinic.service;

import com.example.petclinic.model.Owner;
import com.example.petclinic.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService implements BasicService<Owner> {

    private OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {

        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner add(Owner owner) {

        return this.ownerRepository.save(owner);
    }

    @Override
    public Owner get(Long id) {

        Optional optional = this.ownerRepository.findById(id);

        Owner result = null;
        if (optional.isPresent()) {
            result = (Owner) optional.get();
        }
        return result;
    }

    @Override
    public Owner modify(Owner owner) {

        return this.ownerRepository.save(owner);
    }

    @Override
    public boolean delete(Owner owner) {

        this.ownerRepository.delete(owner);
        return true;
    }

    @Override
    public List<Owner> getAll() {

        return (List<Owner>) ownerRepository.findAll();
    }

    public List<Owner> getOwnerByName(String name) {

        return this.ownerRepository.findOwnerByName(name);
    }
}
