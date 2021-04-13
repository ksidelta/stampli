import React from 'react';

export const Condition = ({ predicate, children }: { children: React.ReactNode; predicate: boolean }) => {
  console.log(`RENDER!: ${predicate}`);
  return <>{predicate ? children : null}</>;
};
