package com.proiect_colectiv.model.ConvertersHibernate;
import com.proiect_colectiv.model.Day;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashSet;
import java.util.Set;


//@Converter(autoApply = true)
public class OpenDaysConverter implements AttributeConverter<Set<Day>, String> {

    @Override
    public String convertToDatabaseColumn(Set<Day> attribute) {
        StringBuilder result= new StringBuilder();
        for(Day day: attribute){
            result.append(Day.valueOf(day.name()).ordinal());
        }
        return result.toString();
    }

    @Override
    public Set<Day> convertToEntityAttribute(String dbData) {
        Set<Day> result=new HashSet<Day>();
        Day[] days=Day.values();
        for(int i=0;i<dbData.length();i++){
            int index=Integer.parseInt(String.valueOf(dbData.charAt(i)));
            result.add(days[index]);
        }
        return result;
    }
}
