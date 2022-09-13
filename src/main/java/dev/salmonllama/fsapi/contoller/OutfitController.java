package dev.salmonllama.fsapi.contoller;

import dev.salmonllama.fsapi.model.Outfit;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OutfitController {

    @GetMapping(URIConstants.GET_OUTFIT)
    public @ResponseBody Outfit getOutfit(@PathVariable("id") String id) {
        Outfit o = new Outfit();
        o.id = "idhere";
        o.link = "link here";

        return o;
    }

    @GetMapping(URIConstants.GET_ALL_OUTFIT)
    public @ResponseBody List<Outfit> getAllOutfits() {
        Outfit o1 = new Outfit();
        o1.id = "o1id";
        o1.link = "o1link";

        Outfit o2 = new Outfit();
        o2.id = "o2id";
        o2.link = "o2link";

        List<Outfit> outfits = new ArrayList<>();
        outfits.add(o1);
        outfits.add(o2);

        return outfits;
    }
}
