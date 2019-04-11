    @ManyToMany(mappedBy = "${rightEntity.variableName}s")
    @Setter @Getter private List<${leftEntity.name}> ${leftEntity.variableName}s;
