import React from "react";
import { Header } from "../components/concrete/Header";
import { MobilePage } from "./base/MobilePage";
import { SimpleButton } from "../components/concrete/buttons/SimpleButton";
import { faArrowRight } from "@fortawesome/free-solid-svg-icons/faArrowRight";
import { faCircle } from "@fortawesome/free-solid-svg-icons/faCircle";

export const EntryPage = () => (
  <MobilePage>
    <Header title={"W czym mogę Ci dzisiaj pomóc?"} icon={faCircle} />
    <SimpleButton text={"Chcę zacząć zbierać pieczątki!"} icon={faArrowRight} />
    <SimpleButton text={"Chcę rozdawać pieczątki!"} icon={faArrowRight} />
    <SimpleButton text={"Chcę dowiedzieć się więcej!"} icon={faArrowRight} />
  </MobilePage>
);
