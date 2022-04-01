import React from 'react';
import styled from 'styled-components';
import { MobilePage } from './base/MobilePage';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons/faArrowLeft';
import { Content } from '../components/simple/container/layout/structure/Content';
import { BusinessLogin } from '../v2/components/complex/login/BusinessLogin';
import { InjectionContext } from '../components/stated/context/InjectionContext';
import Header from "./components/Header";

const Inner = styled.div`
  
`

export const ClientLoginPage = () => (
  <MobilePage>
    <Inner>
        <Header title="Zaloguj Się"/>
    </Inner>
  </MobilePage>
);
