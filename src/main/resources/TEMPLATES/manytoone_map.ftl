    @OneToMany(mappedBy = "${rightEntity.variableName}")
    @Setter @Getter private List<${leftEntity.name}> ${leftEntity.variableName}s;
