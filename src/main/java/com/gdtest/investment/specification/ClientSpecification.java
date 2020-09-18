package com.gdtest.investment.specification;

import com.gdtest.investment.model.Client;
import com.gdtest.investment.model.enums.LegalFormEnum;
import org.springframework.data.jpa.domain.Specification;

public class ClientSpecification {

    public static Specification<Client> nameContain(String name) {
        return (client, cq, cb) -> cb.like(client.get("name"), "%" + name + "%");
    }

    public static Specification<Client> shortNameContain(String shortName) {
        return (client, cq, cb) -> cb.like(client.get("shortName"), "%" + shortName + "%");
    }

    public static Specification<Client> addressContain(String address) {
        return (client, cq, cb) -> cb.like(client.get("address"), "%" + address + "%");
    }

    public static Specification<Client> legalFormEqual(String legalForm) {
        return (client, cq, cb) -> cb.equal(client.get("legalForm"), LegalFormEnum.ofName(legalForm));
    }
}
