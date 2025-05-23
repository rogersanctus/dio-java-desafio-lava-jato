package me.rogerioferreira.lavajato.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.rogerioferreira.lavajato.domain.rules.Constraints;
import me.rogerioferreira.lavajato.infra.models.WithId;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operator implements WithId {
  @Id
  private String id;

  @NotNull
  @Size(min = Constraints.MIN_NAME_LENGTH, max = Constraints.MAX_NAME_LENGTH)
  private String name;
}
