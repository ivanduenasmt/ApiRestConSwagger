package com.ApiRest.toDo.mapper;

public interface IMapper <I, O>{
    public O map(I in);
}
