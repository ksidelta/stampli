import React from 'react';
import styled from "styled-components";
import '../../img/menu_placeholder.jpg';
import BottomBar from "../../components/BottomBar";
import {MobilePage} from "../base/MobilePage";
import StampCard from "../../components/StampCard";

const Inner = styled.div`
    min-height: 100vh;
    display: grid;
    grid-template-rows: 1fr auto;
    > section {
      display: flex;
      justify-content: center;
      align-items: center;
      overflow: hidden;
    }
`

export default function StampsPage() {
    return (
        <MobilePage>
            <Inner>
                <section>
                    <StampCard/>
                </section>
                <BottomBar/>
            </Inner>
        </MobilePage>
    )
}