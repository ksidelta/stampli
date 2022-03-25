# V2

## Introduction 

V2 was created to redefine the project structure to make it easier.

### principles

- Placing
  - things that closely work together should be in one module
  - if something is going to be used only by other module it should be its child
  - meaningful naming must be!

### Practices

- For **simple components**
  - use functional components
  - folders should look like `components/simple/$moduleName/$childModuleName/$meaningfulName`
  - $meaningfulName should be one of these
    - *Component - For anything that uses only React  
    - *State - For MobX components that   
    - *StatefulComponent - Special wrapper class that connects State with Component  
    - \* - should contain ready to use components for **complex components** and **pages**
- For **complex components**
  - they connect many **simple components** to fulfill some business use case 
  - use classes as it is easier to handle MobX inside
  - folders should look like `components/complex/$moduleName/$childModuleName/$meaningfulName`
- Common Principles
  - Normally: Classes should use other classes inward the child modules
