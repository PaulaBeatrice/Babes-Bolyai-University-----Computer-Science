package repository.file;

import domain.Entity;
import domain.validator.Validator;
import repository.memory.InMemoryRepository;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractFileRepository <ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> {
    String fileName;
    public AbstractFileRepository(String fileName, Validator<E> validator) {
        super(validator);
        this.fileName=fileName;
        loadData();
    }

    private void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                List<String> attr = Arrays.asList(linie.split(";"));
                E e = extractEntity(attr);
                super.save((E) e);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract E extractEntity(List<String> attributes);

    protected abstract String createEntityAsString(E entity);

    public E save(E entity){
        E e= (E) super.save((E) entity);
        if (e!=null)
        {
            writeToFile(entity);
        }
        return e;

    }

    public E delete(ID id){
        E entity = super.delete(id);
        if(entity!=null){
            saveAll();
        }
        return entity;
    }

    public E update(E entity){
        E ent = super.update(entity);
        if(ent!=null){
            saveAll();
        }
        return entity;
    }

    private void saveAll() {
        try(BufferedWriter bW = new BufferedWriter(new FileWriter(fileName, false))){
            for(var entity:findAll()){
                bW.write(createEntityAsString(entity));
                bW.newLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void writeToFile(E entity){
        try (BufferedWriter bW = new BufferedWriter(new FileWriter(fileName,true))) {
            bW.write(createEntityAsString(entity));
            bW.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

