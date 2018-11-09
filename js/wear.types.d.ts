/* generated @ 2018-11-09T18:39:15.338 */
/// <reference path='./recyclerview.types.d.ts' />
/// <reference path='./main.types.d.ts' />

declare namespace WearTypes {
  interface BoxInsetLayoutAttributes extends MainTypes.ViewGroupAttributes {
  }

  interface BoxInsetLayoutLayoutParamsAttributes extends MainTypes.ViewGroupLayoutParamsAttributes {
    boxedEdges?: string;
  }

  interface CircularProgressLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }

  interface SwipeDismissFrameLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }

  interface WearableActionDrawerViewAttributes extends WearableDrawerViewAttributes {
    drawerTitle?: string;
  }

  interface WearableDrawerLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }

  interface WearableDrawerViewAttributes extends MainTypes.FrameLayoutAttributes {
    android_elevation?: string;
    enableAutoPeek?: boolean;
  }

  interface WearableNavigationDrawerViewAttributes extends WearableDrawerViewAttributes {
  }

  interface WearableRecyclerViewAttributes extends RecyclerviewTypes.RecyclerViewAttributes {
    bezelWidth?: number;
    circularScrollingGestureEnabled?: boolean;
    scrollDegreesPerScreen?: number;
  }
}
