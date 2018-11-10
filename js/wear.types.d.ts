/* generated @ 2018-11-10T13:56:01.386 */
/// <reference path='./recyclerview.types.d.ts' />
/// <reference path='./main.types.d.ts' />

declare namespace WearTypes {
  export interface BoxInsetLayoutAttributes extends MainTypes.ViewGroupAttributes {
  }

  export interface BoxInsetLayoutLayoutParamsAttributes extends MainTypes.ViewGroupLayoutParamsAttributes {
    boxedEdges?: string;
  }

  export interface CircularProgressLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }

  export interface SwipeDismissFrameLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }

  export interface WearableActionDrawerViewAttributes extends WearableDrawerViewAttributes {
    drawerTitle?: string;
  }

  export interface WearableDrawerLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }

  export interface WearableDrawerViewAttributes extends MainTypes.FrameLayoutAttributes {
    android_elevation?: string;
    enableAutoPeek?: boolean;
  }

  export interface WearableNavigationDrawerViewAttributes extends WearableDrawerViewAttributes {
  }

  export interface WearableRecyclerViewAttributes extends RecyclerviewTypes.RecyclerViewAttributes {
    bezelWidth?: number;
    circularScrollingGestureEnabled?: boolean;
    scrollDegreesPerScreen?: number;
  }
}
