import React from 'react';
import styled from 'styled-components';
import Header from "../../components/Header";
import Box from '@mui/material/Box';
import TextField from '../../components/TextField';
import {MobilePage} from "../base/MobilePage";
import Button from "../../components/Button";
import ButtonText from "../../components/ButtonText";
import {Checkbox, FormControlLabel} from "@mui/material";
import BottomBar from "../../components/BottomBar";

const Inner = styled.div`
  display: grid;
  grid-template-rows: 1fr auto;
  height: 100vh;
  > section {
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
`

export default function RegisterEmailPage() {
    return (
        <MobilePage>
            <Inner>
            <section>
                <ButtonText text="Wyloguj Się" link="/logout"/>
            </section>
            <BottomBar/>
            </Inner>
        </MobilePage>
    )
}