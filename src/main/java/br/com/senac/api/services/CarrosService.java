package br.com.senac.api.services;

import br.com.senac.api.dtos.CarrosFiltroDto;
import br.com.senac.api.dtos.CarrosRequestDto;
import br.com.senac.api.entidades.Carros;
import br.com.senac.api.repositorios.CarrosRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrosService {
    private CarrosRepositorio carrosRepositorio;

    public CarrosService(CarrosRepositorio carrosRepositorio) {
        this.carrosRepositorio = carrosRepositorio;
    }

    public List<Carros> listar(CarrosFiltroDto filtro) {
        if(filtro.getModelo() != null){
            return  carrosRepositorio.findByModelo(filtro.getModelo());
        }
        return carrosRepositorio.findAll();
    }

    public Carros criar(CarrosRequestDto carro) {
        Carros carrosPersist =
                this.carrosRequestDtoParaCarros(carro);

        return carrosRepositorio.save(carrosPersist);
    }

    public Carros atualizar
            (Long id, CarrosRequestDto carro) {
        if(carrosRepositorio.existsById(id)) {
            Carros carroPersist =
                    this.carrosRequestDtoParaCarros(carro);
            carroPersist.setId(id);

            return carrosRepositorio.save(carroPersist);
        }

        throw new RuntimeException("Carro não encontrado!");
    }

    public void deletar(Long id) {
        if(carrosRepositorio.existsById(id)) {
            carrosRepositorio.deleteById(id);
        }

        throw new RuntimeException("Carro nao encontrado!");
    }

    public Carros listarById(Long id){
        if(carrosRepositorio.existsById(id)){
            return carrosRepositorio.findById(id).get();
        }
        throw new RuntimeException("carro não existe");
    }

    private Carros carrosRequestDtoParaCarros
            (CarrosRequestDto entrada) {
        Carros saida = new Carros();
        saida.setModelo(entrada.getModelo());
        saida.setMarca(entrada.getMarca());
        saida.setAno(entrada.getAno());
        saida.setPlaca(entrada.getPlaca());

        return saida;
    }
}
