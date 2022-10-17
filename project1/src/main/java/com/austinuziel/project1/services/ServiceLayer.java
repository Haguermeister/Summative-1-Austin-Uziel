package com.austinuziel.project1.services;

import com.austinuziel.project1.repositories.GameConsoleRepo;
import com.austinuziel.project1.repositories.GameRepo;
import com.austinuziel.project1.repositories.TShirtRepo;
import org.springframework.stereotype.Service;

@Service
public class ServiceLayer {
    private GameRepo gameRepo;
    private GameConsoleRepo gameConsoleRepo;
    private TShirtRepo tShirtRepo;
}
