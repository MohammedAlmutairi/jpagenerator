    @ManyToMany
    @JoinTable(
          name = "${leftEntity.name}_${rightEntity.name}",
          joinColumns = {@JoinColumn(name = "${leftEntity.name}_ID" ,referencedColumnName = "ID")},
          inverseJoinColumns = {@JoinColumn(name = "${rightEntity.name}_ID" ,referencedColumnName = "ID")}
        )
    @Setter @Getter private List<${rightEntity.name}>  ${rightEntity.variableName}s ;

