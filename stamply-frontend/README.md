## How To use

- nvm install
- nvm use
- npm i
- npm start

## Architecture

### Catalogue Structure

- src
    - components - Contains React components.  
        - simple - basic styled components, easily composable
        - concrete - simple components with defined purpose
        - complex - set of concrete components that are supplying some functionality
        - stated - complex components plugged to mobX system
    - css
    - pages - Contains Pages
    - router
    - services
    - utils

### Rules for components and services

### components

They have layered hiererchy

##### concrete-components
- should be easily reausable
- should style components

##### complex-components
- may use any amount of concrete-components
- may use other complex-components
- should not style components

##### stated-components
- may use only one complex-components
- may use global injection context
- may interact with MobX

##### pages
- may only depend on url arguments
- should use only stated-components & concrete-components
